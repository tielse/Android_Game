package chauthoi1010.gmail.com.Tools;

public class Tools {
	/* Lay gia tri ngau nhien tu khoang min->max */
	public static int getRandomIndex(int max, int min) {
		return (int) ((Math.random() * (max - min + 1)) + min);
	}
}
