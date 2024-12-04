package oncall;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            run("0,일",
                    "4,토",
                    "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브"
            );
            assertThat(output()).contains(
                    "[ERROR]",
                    "4월 1일 토 오션" + LINE_SEPARATOR,
                    "4월 2일 일 로이스" + LINE_SEPARATOR,
                    "4월 3일 월 허브" + LINE_SEPARATOR,
                    "4월 4일 화 쥬니" + LINE_SEPARATOR,
                    "4월 5일 수 말랑" + LINE_SEPARATOR
            );
        });
    }

    @Test
    @DisplayName("요일이 월~일이 아닐 경우 에러")
    void 예외_테스트2() {
        assertSimpleTest(() -> {
            run("4,멍", "4,일",
                    "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브"
            );
            assertThat(output()).contains("[ERROR]");
        });
    }

    @Test
    @DisplayName("근무자가 4명 이하인경우 에러")
    void 예외_테스트3() {
        assertSimpleTest(() -> {
            run("4,일",
                    "허브,쥬니,말랑,라온",
                    "라온,말랑,쥬니,허브",
                    "허브,쥬니,말랑,라온,수달",
                    "라온,말랑,쥬니,허브,수달"
            );
            assertThat(output()).contains("[ERROR]");
        });
    }

    @Test
    @DisplayName("근무자가 35명 초과인경우 에러")
    void 예외_테스트4() {
        assertSimpleTest(() -> {
            run("4,일",
                    "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션,냥이",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브,냥이",
                    "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브"
            );
            assertThat(output()).contains("[ERROR]");
        });
    }

    @Test
    @DisplayName("닉네임이 중복인 경우 예외/근무자가 각 1회만 편성되지 않으면 예외")
    void 예외_테스트5() {
        assertSimpleTest(() -> {
            run("4,일",
                    "허브,쥬니,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션,허브",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,쥬니,허브,허브",
                    "허브,쥬니,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,쥬니,허브"
            );
            assertThat(output()).contains("[ERROR]");
        });
    }


    @Test
    @DisplayName("둘 리스트가 일치하지 않는 경우 예외")
    void 예외_테스트6() {
        assertSimpleTest(() -> {
            run("4,일",
                    "허브,쥬니,로지,첵스,아이유,우가,푸주,애쉬,로이스,오션,바니",
                    "오션,로이스,냥이,푸만능,우가,아이크,첵스,라지,쥬니,허브,허브",
                    "허브,쥬니,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,쥬니,허브"
            );
            assertThat(output()).contains("[ERROR]");
        });
    }


    @Test
    void 기능_테스트() {
        assertSimpleTest(() -> {
            run(
                    "4,토",
                    "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브"
            );
            assertThat(output()).contains(
                    "4월 1일 토 오션" + LINE_SEPARATOR,
                    "4월 2일 일 로이스" + LINE_SEPARATOR,
                    "4월 3일 월 허브" + LINE_SEPARATOR,
                    "4월 4일 화 쥬니" + LINE_SEPARATOR,
                    "4월 5일 수 말랑" + LINE_SEPARATOR,
                    "4월 6일 목 라온" + LINE_SEPARATOR,
                    "4월 7일 금 헤나" + LINE_SEPARATOR,
                    "4월 8일 토 애쉬" + LINE_SEPARATOR,
                    "4월 9일 일 푸만능" + LINE_SEPARATOR,
                    "4월 10일 월 우코" + LINE_SEPARATOR,
                    "4월 11일 화 에단" + LINE_SEPARATOR,
                    "4월 12일 수 수달" + LINE_SEPARATOR,
                    "4월 13일 목 파워" + LINE_SEPARATOR,
                    "4월 14일 금 히이로" + LINE_SEPARATOR,
                    "4월 15일 토 우가" + LINE_SEPARATOR,
                    "4월 16일 일 아이크" + LINE_SEPARATOR,
                    "4월 17일 월 마코" + LINE_SEPARATOR,
                    "4월 18일 화 슬링키" + LINE_SEPARATOR,
                    "4월 19일 수 모디" + LINE_SEPARATOR,
                    "4월 20일 목 연어" + LINE_SEPARATOR,
                    "4월 21일 금 깃짱" + LINE_SEPARATOR,
                    "4월 22일 토 첵스" + LINE_SEPARATOR,
                    "4월 23일 일 로지" + LINE_SEPARATOR,
                    "4월 24일 월 리오" + LINE_SEPARATOR,
                    "4월 25일 화 고니" + LINE_SEPARATOR,
                    "4월 26일 수 박스터" + LINE_SEPARATOR,
                    "4월 27일 목 달리" + LINE_SEPARATOR,
                    "4월 28일 금 조이" + LINE_SEPARATOR,
                    "4월 29일 토 해시" + LINE_SEPARATOR,
                    "4월 30일 일 폴로"
            );
        });
    }

    @Test
    void 기능_테스트2_법정공휴일_있는달() {
        assertSimpleTest(() -> {
            run(
                    "5,월",
                    "준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리",
                    "수아,루루,글로,솔로스타,우코,슬링키,참새,도리,준팍,도밥,고니"
            );
            assertThat(output()).contains(
                    "5월 1일 월 준팍" + LINE_SEPARATOR,
                    "5월 2일 화 도밥" + LINE_SEPARATOR,
                    "5월 3일 수 고니" + LINE_SEPARATOR,
                    "5월 4일 목 수아" + LINE_SEPARATOR,
                    "5월 5일 금(휴일) 루루" + LINE_SEPARATOR,
                    "5월 6일 토 수아" + LINE_SEPARATOR
            );
        });
    }

    @Test
    void 기능_테스트3_법정공휴일_있는달() {
        assertSimpleTest(() -> {
            run(
                    "5,화",
                    "준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리",
                    "수아,루루,글로,솔로스타,우코,슬링키,참새,도리,준팍,도밥,고니"
            );
            assertThat(output()).contains(
                    "5월 1일 화 준팍" + LINE_SEPARATOR,
                    "5월 2일 수 도밥" + LINE_SEPARATOR,
                    "5월 3일 목 고니" + LINE_SEPARATOR,
                    "5월 4일 금 수아" + LINE_SEPARATOR,
                    "5월 5일 토 루루" + LINE_SEPARATOR,
                    "5월 6일 일 수아" + LINE_SEPARATOR
            );
        });
    }

    @Test
    void 기능_테스트4_법정공휴일_있는달() {
        assertSimpleTest(() -> {
            run(
                    "10,월",
                    "준팍,도밥,수아,루루,글로,솔로스타,우코,슬링키,참새,도리,고니",
                    "수아,루루,글로,솔로스타,우코,슬링키,참새,도리,준팍,도밥,고니"
            );
            assertThat(output()).contains(
                    "10월 1일 월 준팍" + LINE_SEPARATOR,
                    "10월 2일 화 도밥" + LINE_SEPARATOR,
                    "10월 3일 수(휴일) 수아" + LINE_SEPARATOR,
                    "10월 4일 목 루루" + LINE_SEPARATOR,
                    "10월 5일 금 수아" + LINE_SEPARATOR,
                    "10월 6일 토 루루" + LINE_SEPARATOR
            );
        });
    }

    @Test
    void 기능_테스트4_소수인원_재배치테스트() {
        assertSimpleTest(() -> {
            run(
                    "10,월",
                    "준팍,도밥,수아,루루,글로",
                    "수아,루루,글로,도밥,준팍"
            );
            assertThat(output()).contains("");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
