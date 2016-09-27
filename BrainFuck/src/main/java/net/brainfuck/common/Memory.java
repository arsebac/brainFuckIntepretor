package brainfuck.common;

import java.util.Hashtable;
import java.util.Map;

public class Memory {

	public static final int MAX_CAPACITY = 30000;
	public static final byte MAX_VALUE = Byte.MAX_VALUE;
	public static final byte MIN_VALUE = 0;
	private static final int ARRAY_SIZE = 1000;

	private byte start[] = new byte[ARRAY_SIZE];
	private Map<Integer, Byte> end = new Hashtable<>();
	int index = 0;
	private static Memory instance = new Memory();

	private Memory() {
		if (instance != null)
			throw new IllegalStateException("Already instantiated");
	}

	/**
	 * @return the instance
	 */
	public static Memory getInstance() {
		return instance;
	}

	/**
	 * Override of the clone method because of the singleton
	 * 
	 * @throws CloneNotSupportedException
	 *             because this instance is a singleton
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Cannot clone instance of this class");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		byte current;
		int i=0;
		try {
			for (; i < MAX_CAPACITY; i++) {
				if ((current = get(i)) != 0) {
					builder.append("C" + i + ": " + current + "\n");
				}
			}
		} catch (Exception e) {
			System.out.println(i);
			e.printStackTrace();
		}
		return builder.toString();
	}

	public byte get() throws Exception {
		return get(index);
	}

	private byte get(int index) throws Exception {
		checkIndex(index);
		if (index < ARRAY_SIZE)
			return start[index];
		return (end.get(index - ARRAY_SIZE) != null) ? end.get(index - ARRAY_SIZE) : (byte)0;
	}

	private Memory set(int index, int changeValue) throws Exception {
		if (get(index) > (MAX_VALUE - changeValue) || get(index) < (MIN_VALUE - changeValue))
			throw new Exception("Yolo");
		if (index < ARRAY_SIZE) {
			start[index] += changeValue;
			return this;
		}
		end.put(index - ARRAY_SIZE, (byte) (get(index) + changeValue));
		return this;

	}

	private void checkIndex(int index) throws Exception {
		if (index < 0 || index >= MAX_CAPACITY)
			throw new Exception("Yolo");
	}

	// TODO: 27/09/2016 Check if the memory limit is exceed
	public Memory right() {
		index++;
		return this;
	}

	// TODO: 27/09/2016 Check if the memory limit is exceed
	public Memory left() {
		index--;
		return this;
	}

	public Memory incr() throws Exception {
		return set(index, 1);
	}

	public Memory decr() throws Exception {
		return set(index, -1);
	}

}
