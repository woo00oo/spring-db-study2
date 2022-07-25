package hello.itemservice.transactional.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}