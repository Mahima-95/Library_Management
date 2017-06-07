package com.xavient;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CampMinMax extends Configured implements Tool {

	public static class DurationMapper extends
			Mapper<Object, Text, Text, MinMaxDuration> {

		private Text month = new Text();
		private Integer minduration;
		private Integer maxduration;

		private MinMaxDuration outPut = new MinMaxDuration();

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {

			String[] campaignFields = value.toString().split(",");
			// 10001,'telephone','may','mon',100,1,999,0,'nonexistent','no',0
			month.set(campaignFields[2]);
			minduration = Integer.parseInt(campaignFields[4]);
			maxduration = Integer.parseInt(campaignFields[4]);

			if (month == null || minduration == null || maxduration == null) {
				return;
			}
			try {
				outPut.setMinDuration(minduration);
				outPut.setMaxDuration(maxduration);
				context.write(month, outPut);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static class DurationReducer extends
			Reducer<Text, MinMaxDuration, Text, MinMaxDuration> {
		private MinMaxDuration resultRow = new MinMaxDuration();

		public void reduce(Text key, Iterable values, Context context)
				throws IOException, InterruptedException {
			Integer minduration = 0;
			Integer maxduration = 0;

			resultRow.setMinDuration(null);
			resultRow.setMaxDuration(null);

			for (Object val : values) {
				MinMaxDuration minMaxDuration = (MinMaxDuration) val;
				minduration = ((MinMaxDuration) val).getMinDuration();
				maxduration = ((MinMaxDuration) val).getMaxDuration();
				// get min score

				if (resultRow.getMinDuration() == null
						|| minduration.compareTo(resultRow.getMinDuration()) < 0) {
					resultRow.setMinDuration(minduration);
				} // get min bonus if (resultRow.getMaxDuration()==null ||
					// maxduration.compareTo(resultRow.getMaxDuration())>0) {
				resultRow.setMaxDuration(maxduration);
			}
			context.write(key, resultRow);
		} // end of for loop

	}

	// }

	/*
	 * public static void main(String[] args) throws Exception { Configuration
	 * conf = new Configuration(); Job job = new Job(getConf());
	 * job.setJarByClass(CampaignMinMax.class);
	 * job.setMapperClass(DurationMapper.class);
	 * job.setCombinerClass(DurationReducer.class);
	 * job.setReducerClass(DurationReducer.class);
	 * job.setOutputKeyClass(Text.class);
	 * job.setOutputValueClass(MinMaxDuration.class);
	 * FileInputFormat.addInputPath(job, new Path(args[0]));
	 * FileOutputFormat.setOutputPath(job, new Path(args[1]));
	 * System.exit(job.waitForCompletion(true) ? 0 : 1); }
	 */

	public int run(String[] arg0) throws Exception {
		Job job = new Job(getConf());
		job.setJarByClass(CampMinMax.class);
		job.setMapperClass(DurationMapper.class);
		job.setCombinerClass(DurationReducer.class);
		job.setReducerClass(DurationReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(MinMaxDuration.class);
		FileInputFormat.addInputPath(job, new Path(
				"/home/xavient/mahima/input/file.csv"));
		FileOutputFormat.setOutputPath(job, new Path(
				"/home/xavient/mahima/output"));
		boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new CampMinMax(), args);
		System.exit(res);
	}
}