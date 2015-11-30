public class MyTest {

    //@Test
    public void test() {
        //Assert.assertThat(true, is(true));
        if ((2 + 2) != 4) {
            throw new RuntimeException("Assert failed");
        }
    }
}
