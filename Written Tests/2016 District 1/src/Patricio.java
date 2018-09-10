import java.util.*;
import java.io.*;
import static java.lang.System.*;
// Engine eval runs at 0.001 sec, parser runs at 0.0004 - 0.0 sec

public class Patricio {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("patricio.dat"));
		int amt = in.nextInt();
		while(amt-- > 0) {
			in.nextLine();
			String eq = in.nextLine();
			double maxX = in.nextDouble();
			double maxY = in.nextDouble();
			int maxT = in.nextInt();
			eq = eq.substring(4);
			eq = AddMult(eq);
			Object[] res = {0.0, 0.0, 0, 0.0};
			for(double x = 0; x <= maxX; x+=0.1) {
				for(double y = 0; y <= maxY; y+=0.1) {
					for(int t = 0; t <= maxT; t++) {
						String temp = eq;
						temp = temp.replaceAll("x", Double.toString(x));
						temp = temp.replaceAll("y", Double.toString(y));
						temp = temp.replaceAll("t", Integer.toString(t));
						double z = new Parser(temp).parse();
						if(z < Double.MAX_VALUE && z > (double)res[3]) {
							res[0] = x;
							res[1] = y;
							res[2] = t;
							res[3] = z;
						}
					}
				}
			}
			out.printf("Patricio should launch at x=%.1f and y=%.1f at t=%d at a height of %.1f.\n", res[0], res[1], res[2], res[3]);
		}
		in.close();
	}
	
	public static String AddMult(String eq) {
		for(int i = 0; i < eq.length(); i++) {
			try {
				if((Character.isAlphabetic(eq.charAt(i)) && (Character.isDigit(eq.charAt(i+1)) || Character.isAlphabetic(eq.charAt(i+1)))) || (Character.isDigit(eq.charAt(i)) && Character.isAlphabetic(eq.charAt(i+1)))) {
					eq = eq.substring(0, i+1) + "*" + eq.substring(i+1);
				}
			}catch(Exception e) {}
		}
		return eq;
	}
}

class Parser {
	int pos = -1, c;
	String str;

	public Parser(String str) {
		this.str = str;
	}

	void eatChar() {
		c = (++pos < str.length()) ? str.charAt(pos) : -1;
	}

	void eatSpace() {
		while (Character.isWhitespace(c))
			eatChar();
	}

	double parse() {
		eatChar();
		double v = parseExpression();
		if (c != -1)
			throw new RuntimeException("Unexpected: " + (char) c);
		return v;
	}

	double parseExpression() {
		double v = parseTerm();
		for (;;) {
			eatSpace();
			if (c == '+') {
				eatChar();
				v += parseTerm();
			} else if (c == '-') {
				eatChar();
				v -= parseTerm();
			} else {
				return v;
			}
		}
	}

	double parseTerm() {
		double v = parseFactor();
		for (;;) {
			eatSpace();
			if (c == '/') {
				eatChar();
				v /= parseFactor();
			} else if (c == '*') {
				eatChar();
				v *= parseFactor();
			} else {
				return v;
			}
		}
	}

	double parseFactor() {
		double v;
		boolean negate = false;
		eatSpace();
		for (;;) {
			if (c == '+' || c == '-') {
				negate = c == '-';
				eatChar();
				eatSpace();
			}
			if (c == '(') {
				eatChar();
				v = parseExpression();
				if (c == ')')
					eatChar();
			} else {
				StringBuilder sb = new StringBuilder();
				while ((c >= '0' && c <= '9') || c == '.') {
					sb.append((char) c);
					eatChar();
				}
				if (sb.length() == 0)
					throw new RuntimeException("Unexpected: " + (char) c);
				v = Double.parseDouble(sb.toString());
			}
			eatSpace();
			if (c == '^') {
				eatChar();
				v = Math.pow(v, parseFactor());
			}
			if (negate)
				v = -v;
			return v;
		}
	}
}