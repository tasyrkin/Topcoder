
import java.util.*;

public class RotatedClock{
	public String getEarliest(int hourHand, int minuteHand){
		ArrayList<String> times = new ArrayList<String>();
		for(int i = 0; i <= 12; i++){			
			int currHourHand = hourHand+30*i;
			int currMinuteHand = minuteHand+30*i;
			if(currHourHand>=360){
				currHourHand -= 360;
			}
			if(currMinuteHand>=360){
				currMinuteHand -= 360;
			}
			int hourMinutePart = currHourHand%30;
			int hourMinutes = hourMinutePart*2*6;
			int minutesMinutes = currMinuteHand;
			if(hourMinutes==minutesMinutes){
				String hoursStr = (currHourHand/30 < 10) ? ("0"+currHourHand/30):(""+currHourHand/30);
				String minStr = (currMinuteHand/6<10) ? ("0"+currMinuteHand/6):(""+currMinuteHand/6);
				String time = hoursStr+":"+minStr;
				times.add(time);
			}
		}
		if(times.size()==0){
			return "";
		}
		String[]timesArr = times.toArray(new String[times.size()]);
		Arrays.sort(timesArr);
		return timesArr[0];
	}
	public static void main(String[]args){
		RotatedClock rc = new RotatedClock();
		String res = rc.getEarliest(19, 19);
		//String res = rc.getEarliest(70, 300);
		System.out.println(res);
	}
}