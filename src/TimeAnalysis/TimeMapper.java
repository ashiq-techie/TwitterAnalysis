package TimeAnalysis;
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
public class TimeMapper extends Mapper<Object, Text, Text, IntWritable> { 
    private int length = 0;
    private IntWritable one = new IntWritable(1);
    private Text data = new Text();
    private String range;
    private int startRange;
    private int endRange;
    private int category = 0;
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
	    	String[] date_array = value.toString().split(";");
		String[] date_var = date_array[1].split(", ");
		String dateStr = date_var[0];
		data.set(dateStr);
		context.write(data, one);
    }
}
