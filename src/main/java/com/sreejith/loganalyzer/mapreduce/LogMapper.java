/*
 * Copyright 2014 Sreejith Pillai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sreejith.loganalyzer.mapreduce;

import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sreejith.loganalyzer.parser.ParseLog;

public class LogMapper extends
		Mapper<LongWritable, Text, IntWritable, IntWritable> {

	private static Logger logger = LoggerFactory.getLogger(LogMapper.class);
	private IntWritable hour = new IntWritable();
	private final static IntWritable one = new IntWritable(1);
	private static Pattern logPattern = Pattern
			.compile("([^ ]*) ([^ ]*) ([^ ]*) \\[([^]]*)\\]"
					+ " \"([^\"]*)\""
					+ " ([^ ]*) ([^ ]*).*");

	public void map(LongWritable key, Text value, Context context)
			throws InterruptedException, IOException {
		logger.info("Mapper started");
		String line = ((Text) value).toString();
		Matcher matcher = logPattern.matcher(line);
		if (matcher.matches()) {
			String timestamp = matcher.group(4);
			try {
				hour.set(ParseLog.getHour(timestamp));
			} catch (ParseException e) {
				logger.warn("Exception", e);
			}
			context.write(hour, one);
		}
		logger.info("Mapper Completed");
	}
}
