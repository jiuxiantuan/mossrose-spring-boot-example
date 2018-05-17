package com.jiuxian.mossrose.springboot.example.jobs;

import com.google.common.collect.Lists;
import com.jiuxian.boot.mossrose.autoconfigure.Job;
import com.jiuxian.mossrose.job.StreamingJob;
import com.jiuxian.mossrose.util.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Job(id = "StreamingExampleJob", cron = "0 * * * * ?", group = "example")
public class StreamingExampleJob implements StreamingJob<String, Integer> {

	private static final Logger LOGGER = LoggerFactory.getLogger(StreamingExampleJob.class);

	private static final List<String> LIST = Lists.newArrayList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");

	@Override
	public Streamer<String, Integer> streamer() {
		return new Streamer<String, Integer>() {

            @Override
            public Tuple<String, Integer> next(Integer mark) {
                int index = mark != null ? mark + 1 : 0;
                if(index > LIST.size() - 1) {
                    return null;
                }

                return new Tuple(LIST.get(index), index);
            }

			private int index = 0;

		};
	}

	@Override
	public Executor<String> executor() {
		return new Executor<String>() {

			@Override
			public void execute(String item) {
				LOGGER.info("StreamingJob: " + item);
			}
		};
	}

}
