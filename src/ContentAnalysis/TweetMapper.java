package ContentAnalysis;
import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.commons.lang.StringUtils;
import java.text.Normalizer;
import java.util.Formatter;
public class TweetMapper extends Mapper<Object, Text, Text, IntWritable> { 
    private int length = 0;
    private IntWritable one = new IntWritable(1);
    private Text data = new Text();
    private String range;
    private int startRange;
    private int endRange;
    private int category = 0;
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
	String dump;
	dump = value.toString();
	if(StringUtils.ordinalIndexOf(dump,";",4)>-1){
        	int startIndex = StringUtils.ordinalIndexOf(dump,";",3) + 1;
        	String tweet = dump.substring(startIndex,dump.lastIndexOf(';'));
		//String normalizedTweet = Normalizer.normalize(tweet, Normalizer.Form.NFD);
		//int counter = 0;
		//StringBuilder retStr = new StringBuilder();
		//Formatter f = new Formatter();
  		//char[] c = tweet.toCharArray();
        	//StringBuilder res = new StringBuilder();
        	//for (int i=0; i<tweet.length(); i++) {
    			
			//if (c < 128) {
    			//     // b.append(c);
    			//        // } else {
    	              		//f.format("\\\\u%04x", (int) c[i]);
    			//                 // }
    			//
   		//}
		//String str = f.toString();
 		//length = str.length() - str.replace("\\\\", "").length();
		length = tweet.length();
		if(length>140){
			data.set("141");
		} else {
			category = length/5;
			// startRange = (category*5).toString();
			if(length%5 == 0){
				startRange = (((category-1)*5)+1);
			} else {
				startRange = ((category*5)+1);
			}

			// endRange = ((category*5)+5).toString();
			endRange = startRange+4;
			range = startRange + "-"+ endRange;
			data.set(range);
		}
		context.write(data, one);
	}
    }
}
