package HashtagAnalysis;
import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class HashMapper extends Mapper<Object, Text, Text, IntWritable> { 
    	private final IntWritable one = new IntWritable(1);
    	private Text data = new Text();
	private static  ArrayList<String> cName = new ArrayList<String>();
    	private static  ArrayList<String> cCode = new ArrayList<String>();
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String tweets[] = value.toString().split(";");
		countCode();
		cName();
		String search = "team go goteam bmx jjoo mgl bolt awesome respect legend pride proud";
		String[] searchArray = search.split(" ");
		String hashtag =tweets[2].toString();
		hashtag = hashtag.toUpperCase();
		for(int i=0;i<cCode.size();i++){
			for(int j=0; j<searchArray.length; j++){
				if(hashtag.indexOf(cName.get(i).toUpperCase()) != -1){
                			data.set(cName.get(i));
                			context.write(data,one);
        			}
        			else if (hashtag.indexOf(searchArray[j].toUpperCase()+cCode.get(i).toUpperCase()) != -1) {
					data.set(cName.get(i));	
					context.write(data, one);	
				} else {
					//do nothing
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

