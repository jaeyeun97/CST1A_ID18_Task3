package uk.ac.cam.cl.id.group18.task3;

public class ThreeHourWeather{
	private String D;
	private int F;
	private int G;
	private int H;
	private int Pp;
	private int S;
	private int T;
	private String V;
	private int W;
	private int U;
	private int $;

	public String direction(){
		return D;
	}
	
	public int weatherType(){
		return W;
	}

	public int UV(){
		return U;
	}
	
	public String visibility(){
		return V;
	}
	
	public int temperature(){
		return T;
	}

	public int windSpeed(){
		return S;
	}

	//probability of precipitation
	public int precipProb(){
		return Pp;
	}

	public int humidity(){
		return H;
	}

	public int windGusts(){
		return G;
	}

	public int feelsLikeTemp(){
		return F;
	}

	public int minutesAfterMidnight(){
		return $;
	}
}
