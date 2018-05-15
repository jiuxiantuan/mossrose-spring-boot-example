package com.jiuxian.mossrose.springboot.example.jobs;

import com.jiuxian.boot.mossrose.autoconfigure.Job;
import com.jiuxian.mossrose.job.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Job(id = "SimpleExampleJob", cron = "0/5 * * * * ?", group = "example")
public class SimpleExampleJob implements SimpleJob {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleExampleJob.class);

	@Override
	public Executor executor() {
		return new Executor() {

			@Override
			public void execute() {
				LOGGER.info("SimpleJob");
			}
		};
	}

}
