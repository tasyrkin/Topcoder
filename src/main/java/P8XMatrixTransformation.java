
public class P8XMatrixTransformation {

	public String solve(String[] original, String[] target) {
		int or = 0;
		int tr = 0;
		for (int i = 0; i < original.length; i++) {
			for (int j = 0; j < original[0].length(); j++) {
				if (original[i].charAt(j) == '1')
					or++;
				if (target[i].charAt(j) == '1')
					tr++;
			}
		}
		if (or == tr) {
			return "YES";
		}
		return "NO";
//		for (int j = 0; j < original[0].length(); j++) {
//			int or = 0;
//			int tr = 0;
//			for (int i = 0; i < original.length; i++) {
//				if (original[i].charAt(j) == '1')
//					or++;
//				if (target[i].charAt(j) == '1')
//					tr++;
//			}
//			if (or == tr) {
//				return "YES";
//			}
//		}
//		return "NO";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
