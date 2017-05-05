package com.xavient.dip.sources_s3
import java.util.function.Function
import java.util.function.Consumer
import com.amazonaws.services.s3.model.GetObjectRequest
import org.apache.hadoop.tools.DistCp
import org.apache.hadoop.tools.DistCpOptions
import org.apache.hadoop.fs.Path
import com.sun.prism.impl.Disposer.Target
import org.apache.hadoop.conf.Configuration
import java.net.URI
import java.util.ArrayList
import org.apache.hadoop.fs.FileSystem

object CopyFromLocalSystem {

  def main(args: Array[String]): Unit = {

    val configuration = new Configuration

    configuration.set(" fs.defaultFS", "hdfs://10.5.3.167:8020/");
    configuration.set("fs.hdfs.impl", classOf[org.apache.hadoop.hdfs.DistributedFileSystem].getName)
    //configuration.set("fs.file.impl", classOf[org.apache.hadoop.fs.LocalFileSystem].getName)

    val fs = FileSystem.get(configuration);
    fs.copyFromLocalFile(new Path("/home/hdfs/anaconda-ks.cfg"),
      new Path("/user/hdfs/dir"));
    print("complete")

  }

}