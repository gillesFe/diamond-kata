package kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Diamond {
	
	private static final String WHITE_SPACE = " ";

	private static final String LINE_BREAK = "\n";
	
	private static final List<String> alphabet = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
			"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
	
	private String diamondCreated;
	
	private Diamond(String diamondCreated) {
		this.diamondCreated = diamondCreated;
	}

	public static Diamond from(String input) {
		return new Diamond(buildDiamondFrom(input));
	}
	
	public String get() {
		return diamondCreated;
	}
	
	private static String buildDiamondFrom(String input) {
		return buildUpperDiamondFrom(input) + buildBottomDiamondFrom(input);
	}

	private static String buildUpperDiamondFrom(String input) {
		StringBuilder upperDiamondBuilder = new StringBuilder();
		int spacesNumber = 0;
		for(String letter : alphabet) {
			upperDiamondBuilder.append(buildUpperLineDiamond(spacesNumber, letter));
			spacesNumber = incrementSpacesNumber(spacesNumber);
			
			if (letter.equals(input)) {
				break;
			}
		}
		return upperDiamondBuilder.toString();
	}

	private static String buildUpperLineDiamond(int spacesNumber, String letter) {
		if (spacesNumber == 0) {
			return WHITE_SPACE + letter + LINE_BREAK;
		} else if (spacesNumber == 1) {
			return letter + WHITE_SPACE + letter + LINE_BREAK;
		}
		return letter + buildCharsInLineSeparator(spacesNumber) + letter + LINE_BREAK;
	}
	
	private static String buildBottomLineDiamond(int spacesNumber, String letter) {
		if (spacesNumber <= 0) {
			return  WHITE_SPACE + letter + LINE_BREAK;
		} else if (spacesNumber == 1) {
			return letter + WHITE_SPACE + letter + LINE_BREAK;
		}
		return letter + buildCharsInLineSeparator(spacesNumber) + letter + LINE_BREAK;
	}

	private static int incrementSpacesNumber(int charsInLineSeparator) {
		return modifySpacesNumber(charsInLineSeparator, 2);
	}
	
	private static int decrementSpacesNumber(int charsInLineSeparator) {
		return modifySpacesNumber(charsInLineSeparator, -2);
	}
	
	private static int modifySpacesNumber(int charsInLineSeparator, int spaceNumber) {
		int charsInLineSeparatorIncreased = 0;
		if (charsInLineSeparator == 0) {
			charsInLineSeparatorIncreased++;
		} else {
			charsInLineSeparatorIncreased = charsInLineSeparator + spaceNumber;
		}
		return charsInLineSeparatorIncreased;
	}

	private static String buildCharsInLineSeparator(int spaceNumber) {
		StringBuilder charsInLineSeparator = new StringBuilder();
		for (int i = 0; i < spaceNumber; i++) {
			charsInLineSeparator.append(WHITE_SPACE);
		}
		return charsInLineSeparator.toString();
	}

	private static String buildBottomDiamondFrom(String input) {
		StringBuilder bottomDiamondBuilder = new StringBuilder();
		AtomicBoolean isInputLetter = new AtomicBoolean(false);
		AtomicInteger spacesNumber = new AtomicInteger(calculateSpacesOfLetterBeforeInputLetter(input));
		
		buildReverseFrom(alphabet).stream().forEach(letter -> {
			if (isInputLetter.get()) {
				bottomDiamondBuilder.append(buildBottomLineDiamond(spacesNumber.intValue(), letter));
				spacesNumber.set(decrementSpacesNumber(spacesNumber.intValue()));
			}
			if (letter.equals(input)) {
				isInputLetter.set(true);
			}
		});
		return bottomDiamondBuilder.toString();
	}

	private static List<String> buildReverseFrom(List<String> alphabet) {
		List<String> alphabetReversed = new ArrayList<>(alphabet);
		Collections.reverse(alphabetReversed);
		return alphabetReversed;
	}

	private static int calculateSpacesOfLetterBeforeInputLetter(String input) {
		int spacesInLineBottomDiamond = 0;
		for(String letter : alphabet) {
			if (letter.equals(input)) {
				break;
			}
			
			if (letter.equals("B")) {
				spacesInLineBottomDiamond += 1;
			}
			
			if (isNotSpecificLetter(letter)) {
				spacesInLineBottomDiamond += 2;
			}
		}
		return spacesInLineBottomDiamond;
	}

	private static boolean isNotSpecificLetter(String letter) {
		return !letter.equals("A") && !letter.equals("B");
	}

}
