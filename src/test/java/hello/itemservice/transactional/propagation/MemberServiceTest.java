package hello.itemservice.transactional.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired LogRepository logRepository;

    /**
     * memberService    @Transactional : OFF
     * memberRepository @Transactional : ON
     * logRepository    @Transactional : ON
     */
    @Test
    void outerTxOff_success() {
        //given
        String userName = "outerTxOff_success";

        //when
        memberService.joinV1(userName);

        //then
        assertTrue(memberRepository.find(userName).isPresent());
        assertTrue(logRepository.find(userName).isPresent());
    }

    /**
     * memberService    @Transactional : OFF
     * memberRepository @Transactional : ON
     * logRepository    @Transactional : ON Exception
     */
    @Test
    void outerTxOff_fail() {
        //given
        String userName = "로그예외_outerTxOff_fail";

        //when
        assertThatThrownBy(() -> memberService.joinV1(userName))
                .isInstanceOf(RuntimeException.class);

        //then
        assertTrue(memberRepository.find(userName).isPresent());
        assertTrue(logRepository.find(userName).isEmpty());
    }

    /**
     * memberService    @Transactional : ON
     * memberRepository @Transactional : OFF
     * logRepository    @Transactional : OFF
     */
    @Test
    void singleTx() {
        //given
        String userName = "singleTx";

        //when
        memberService.joinV1(userName);

        //then
        assertTrue(memberRepository.find(userName).isPresent());
        assertTrue(logRepository.find(userName).isPresent());
    }

    /**
     * memberService    @Transactional : ON
     * memberRepository @Transactional : ON
     * logRepository    @Transactional : ON
     */
    @Test
    void outerTxOn_success() {
        //given
        String userName = "outerTxOn_success";

        //when
        memberService.joinV1(userName);

        //then
        assertTrue(memberRepository.find(userName).isPresent());
        assertTrue(logRepository.find(userName).isPresent());
    }

    /**
     * memberService    @Transactional : ON
     * memberRepository @Transactional : ON
     * logRepository    @Transactional : ON Exception
     */
    @Test
    void outerTxOn_fail() {
        //given
        String userName = "로그예외_outerTxOn_fail";

        //when
        assertThatThrownBy(() -> memberService.joinV1(userName))
                .isInstanceOf(RuntimeException.class);

        //then: 모든 데이터가 롤백 된다.
        assertTrue(memberRepository.find(userName).isEmpty());
        assertTrue(logRepository.find(userName).isEmpty());
    }

    /**
     * memberService    @Transactional : ON
     * memberRepository @Transactional : ON
     * logRepository    @Transactional : ON Exception
     */
    @Test
    void recoverException_fail() {
        //given
        String userName = "로그예외_recoverException_fail";

        //when
        assertThatThrownBy(() -> memberService.joinV2(userName))
                .isInstanceOf(UnexpectedRollbackException.class);

        //then: 모든 데이터가 롤백 된다.
        assertTrue(memberRepository.find(userName).isEmpty());
        assertTrue(logRepository.find(userName).isEmpty());
    }

}