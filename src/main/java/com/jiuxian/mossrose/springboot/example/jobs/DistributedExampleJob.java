package com.jiuxian.mossrose.springboot.example.jobs;

import com.google.common.base.Splitter;
import com.jiuxian.boot.mossrose.autoconfigure.Job;
import com.jiuxian.mossrose.job.DistributedJob;
import com.jiuxian.mossrose.springboot.example.bean.IBusinessBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Job(id = "DistributedExampleJob", cron = "0/15 * * * * ?", group = "example")
public class DistributedExampleJob implements DistributedJob<String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleExampleJob.class);

	@Autowired
	private IBusinessBean businessBean;

	@Override
	public Slicer<String> slicer() {
		return new Slicer<String>() {

			@Override
			public List<String> slice() {
				return Splitter.on(" ").splitToList("A B C D E F G H I J K L M N O P Q R S T U");
			}
		};
	}

	@Override
	public Executor<String> executor() {
		return new Executor<String>() {

			@Override
			public void execute(String item) {
				LOGGER.info("DistributedJob: {}", businessBean.echo(item));
			}
		};
	}

}
