module com.mygroup.language {
    exports com.mygroup.language to com.mygroup.hmi, com.mygroup.report, com.mygroup.main;
	 opens com.mygroup.language to com.mygroup.hmi, com.mygroup.report;
}