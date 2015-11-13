package AverageAnalysis;
import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.commons.lang.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class AverageMapper extends Mapper<Object, Text, Text, IntWritable> { 
    private int length;
    private Text data = new Text();
    private String range;
    private int startRange;
    private int endRange;
    private int category = 0;
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
    		String dump = value.toString();
		if(StringUtils.ordinalIndexOf(dump,";",4)>-1){
        		int startIndex = StringUtils.ordinalIndexOf(dump,";",3) + 1;
        		length = dump.substring(startIndex,dump.lastIndexOf(';')).length();
			if(length > 140){
		                data.set("Average");
        	        	IntWritable lengthWritable = new IntWritable(length);
        		        context.write(data, lengthWritable);
			}
      		}
    }
}
