package com.naver.kakao.year2018;

import com.naver.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class NewsClusteringTest {
    private NewsClustering newsClustering;

    @BeforeEach
    void setUp() {
        newsClustering = new NewsClustering();
    }

    @ParameterizedTest
    @EnumSource(value = Parameter.class)
    void test1(Parameter parameter) {
        //given
        int expected = parameter.solution;

        //when
        int actual = newsClustering.solution(parameter.str1, parameter.str2);

        //then
        assertEquals(expected, actual);
    }

    enum Parameter {
        TEST1("FRANCE", "french", 16384),
        TEST2("handshake", "shake hands", 65536),
        TEST3("aa1+aa2", "AAAA12", 43690),
        TEST4("E=M*C^2", "e=m*c^2", 65536);

        private final String str1;
        private final String str2;
        private final int solution;

        Parameter(String str1, String str2, int solution) {
            this.str1 = str1;
            this.str2 = str2;
            this.solution = solution;
        }
    }
}