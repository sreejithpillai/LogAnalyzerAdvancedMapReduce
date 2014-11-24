LogAnalyzerAdvancedMapReduce
============================

<h3><b>MapReduce implementing Partitioners and Combiners</b></h3>

License
 
Apache licensed.
<br>
<h4>Partitioners</h4>
Partitioners are responsible for dividing up the intermediate key space and assigning intermediate key-value pairs to reducers. In other words, the partitioner specifies the task to which an intermediate key-value pair must be copied. Within each reducer, keys are processed in sorted order.
> job.setPartitionerClass(LogPartitioner.class);

<br>
<h4>Combiners</h4>
Combiners are an optimization in MapReduce that allow for local aggregation before the shuffle and sort phase. The primary goal of combiners is to save as much bandwidth as possible by minimizing the number of key/value pairs that will be shuffled across the network between mappers and reducers.
> job.setCombinerClass(LogReducer.class);

<br>
Check src/test/resource/SampleLog.txt to see demofile.
<br>
Execute the job as <br>
> hadoop jar LogAnalyzerAdvancedMapReduce-0.0.1-SNAPSHOT.jar in /logOpPartitioned
 <br>
 
The output of the job in HDFS will have two output files from two reducers.
