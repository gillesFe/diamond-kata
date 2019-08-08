package kata;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class DiamondTest {

	@Test
	public void should_return_diamond_from_letter_A() throws Exception {
		Assertions.assertThat(Diamond.from("A").get()).isEqualTo(" A\n");
	}
	
	@Test
	public void should_return_diamond_from_letter_B() throws Exception {
		Assertions.assertThat(Diamond.from("B").get()).isEqualTo(" A\nB B\n A\n");
	}
	
	@Test
	public void should_return_diamond_from_letter_C() throws Exception {
		Assertions.assertThat(Diamond.from("C").get()).isEqualTo(" A\nB B\nC   C\nB B\n A\n");
	}
	
	@Test
	public void should_return_diamond_from_letter_D() throws Exception {
		Assertions.assertThat(Diamond.from("D").get()).isEqualTo(" A\nB B\nC   C\nD     D\nC   C\nB B\n A\n");
	}
	
	@Test
	public void should_return_diamond_from_letter_E() throws Exception {
		Assertions.assertThat(Diamond.from("E").get()).isEqualTo(" A\nB B\nC   C\nD     D\nE       E\nD     D\nC   C\nB B\n A\n");
	}
	
	@Test
	public void should_return_diamond_from_letter_F() throws Exception {
		Assertions.assertThat(Diamond.from("F").get()).isEqualTo(" A\nB B\nC   C\nD     D\nE       E\nF         F\nE       E\nD     D\nC   C\nB B\n A\n");
	}
	
}
