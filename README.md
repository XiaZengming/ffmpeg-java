# ffmpeg-java
Java Interface for FFmpeg Command-line



获取媒体信息
```java
import com.alibaba.fastjson.JSON
import com.itshidu.ffmpeg.FFprobe
import com.itshidu.ffmpeg.probe.FFmpegFormat
import com.itshidu.ffmpeg.probe.FFmpegProbeResult
import com.itshidu.ffmpeg.probe.FFmpegStream

class GetMediaInformation {
    final static String FFMPEG_HOME = "D:/Documents/ProgramFiles/ffmpeg-20190219-ff03418-win64-static/bin/"
    public static void main(String[] args) {


        FFprobe ffprobe = new FFprobe(FFMPEG_HOME+"ffprobe.exe")
        FFmpegProbeResult probeResult = ffprobe.probe("D:/abc/002.mp4")

        FFmpegFormat format = probeResult.getFormat()
        System.out.format("%nFile: '%s' ; Format: '%s' ; Duration: %.3fs",
                format.filename,
                format.format_long_name,
                format.duration
        );

        FFmpegStream stream = probeResult.getStreams().get(0);
        System.out.format("%nCodec: '%s' ; Width: %dpx ; Height: %dpx",
                stream.codec_long_name,
                stream.width,
                stream.height
        );
        println("----------------")
        println(JSON.toJSONString(format))
        println(JSON.toJSONString(stream))
    }
}
```


