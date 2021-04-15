package algstudent.s6;

public class Song {

	private String id;
	private int time;
	private int score;
	
	public Song(String id, int time, int score) {
		this.id = id;
		this.time = time;
		this.score = score;
	}
	
	public double rating() {
		return score / time;
	}
	
	public String getId() {
		return id;
	}

	public int getTime() {
		return time;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		String minutes = time/60 + "";
		String seconds = time%60 < 10 ? "0" + time%60 : time%60 + "";
		
		return String.format("id: %s seconds: %s:%s score: %d", id, minutes, seconds, score);
	}

}
