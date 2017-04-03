package syllableCounter;

public class SimpleSyllableCounter {
	private char[] vowel = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };

	private char[] vowelWithY = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U', 'y', 'Y' };

	public boolean isVowelOrY(char c) {
		for (char v : vowelWithY) {
			if (c == v) {
				return true;
			}
		}
		return false;
	}

	public boolean isVowel(char c) {
		for (char v : vowel) {
			if (c == v) {
				return true;
			}
		}
		return false;
	}

	public int countSyllables(String word) {
		int syllables = 0;
		char c = ' ';
		State state = State.START;
		for (int k = 0; k < word.length(); k++) {
			c = word.charAt(k);
			if (c == '\'')
				continue;
			switch (state) {
			case CONSONANT:
				if (k == word.length() - 1 && (c == 'e'|| c == 'E')) {
					if(syllables<1){
						syllables++;
					}
				} 
				else if (isVowelOrY(c)) {
					state = State.SINGLE_VOVEL;
					syllables++;
				} else if (Character.isLetter(c)) {
					state = State.CONSONANT;
				} else if (c == '-') {
					state = State.HYPHEN;
				} else {
					state = State.NONWORD;
				}
				break;
			case SINGLE_VOVEL:
				if (isVowel(c)) {
					state = State.MULTIVOWEL;
				} else if (Character.isLetter(c)) {
					state = State.CONSONANT;
				} else if (c == '-') {
					state = State.HYPHEN;
				} else
					state = State.NONWORD;
				break;
			case MULTIVOWEL:
				if (isVowel(c)) {
					state = State.MULTIVOWEL;
				} else if (Character.isLetter(c)) {
					state = State.CONSONANT;
				} else if (c == '-') {
					state = State.HYPHEN;
				} else
					state = State.NONWORD;
				break;
			case START:
				if (isVowel(c)) {
					state = State.SINGLE_VOVEL;
					syllables++;
				} else if (Character.isLetter(c)) {
					state = State.CONSONANT;
				} else if (c == '-') {
					state = State.NONWORD;
				} else {
					state = State.NONWORD;
				}
				break;
			case HYPHEN:
				if (isVowel(c)) {
					state = State.SINGLE_VOVEL;
					syllables++;
				} else if (Character.isLetter(c)) {
					state = State.CONSONANT;
				} else if (c == '-') {
					state = State.NONWORD;
				} else {
					state = State.NONWORD;
				}
				break;
			case NONWORD:
				syllables = 0;
				break;
			}
		}return syllables;

	}

	public static void main(String[] args) {
		SimpleSyllableCounter a = new SimpleSyllableCounter();
		System.out.println(a.countSyllables("ewe"));
	}
}
