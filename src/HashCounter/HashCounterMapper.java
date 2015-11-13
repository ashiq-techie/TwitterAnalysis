package HashCounter;
import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class HashCounterMapper extends Mapper<Object, Text, Text, IntWritable> { 
    private final IntWritable one = new IntWritable(1);
    private Text data = new Text();
	private static  ArrayList<String> cName = new ArrayList<String>();
    private static  ArrayList<String> cCode = new ArrayList<String>();
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

	String tweets[] = value.toString().split(";");
	countCode();
	cName();
	String[] hashtag_array =tweets[2].toString().split(" ");
	for(int i=0;i<cCode.size();i++){
		for (int j=0;j<hashtag_array.length;j++) {
			if (hashtag_array[j].contains(cCode.get(i).toUpperCase())||hashtag_array[j].toUpperCase().contains(cName.get(i).toUpperCase())) {
				//String before = hashtag_array[j].substring(0, hashtag_array[j].indexOf(cCode.get(i).toUpperCase()));
				//String after = hashtag_array[j].substring(hashtag_array[j].indexOf(cCode.get(i).toUpperCase()) + cCode.get(i).toUpperCase().length());
				//if (before.isEmpty() || before.length() <= 1) {
				//	data.set(after);	
				//	context.write(data, one);
				//} else {
				data.set(hashtag_array[j].toString());
				context.write(data, one);
				//}
			}			
		}	
	}	
}



public static ArrayList<String> countCode(){
		String[] locales = Locale.getISOCountries();
		
		if(cCode.size()  < 1){
			for (String countryCode : locales) {

				Locale obj = new Locale("", countryCode);
				cCode.add(obj.getCountry());

			}
		}
		return cCode;
   }
	 
   public  static ArrayList<String> cName(){
		 String[] locales = Locale.getISOCountries();
		if(cName.size() < 1){
			 for (String countryCode : locales) {

				Locale obj = new Locale("", countryCode);
				
				cName.add(obj.getDisplayCountry());

			}

		}
		return cName;
   } 
}

