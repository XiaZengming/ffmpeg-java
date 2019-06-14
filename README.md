# ffmpeg-java
Java Interface for FFmpeg Command-line



获取媒体信息
```java
import com.alibaba.fastjson.JSON
import com.itshidu.ffmpeg.FFprobe
import com.itshidu.ffmpeg.probe.FFmpegFormat
import com.itshidu.ffmpeg.probe.FFmpegProbeResult
import com.itshidu.ffmpeg.probe.FFmpegStream

public class GetMediaInformation {
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


视频转码
```java
import com.itshidu.ffmpeg.FFmpeg
import com.itshidu.ffmpeg.FFmpegExecutor
import com.itshidu.ffmpeg.FFprobe
import com.itshidu.ffmpeg.builder.FFmpegBuilder

public class VideoEncoding {
    final static String FFMPEG_HOME = "D:/Documents/ProgramFiles/ffmpeg-20190219-ff03418-win64-static/bin/";
    public static void main(String[] args) {
        FFmpeg ffmpeg = new FFmpeg(FFMPEG_HOME+"ffmpeg.exe");
        FFprobe ffprobe = new FFprobe(FFMPEG_HOME+"ffprobe.exe");

        FFmpegBuilder builder = new FFmpegBuilder()

                .setInput("002.mp4")     // Filename, or a FFmpegProbeResult
                .overrideOutputFiles(true) // Override the output if it exists

                .addOutput("output.mp4")   // Filename for the destination
                .setFormat("mp4")        // Format is inferred from filename, or can be set
                .setTargetSize(250_000)  // Aim for a 250KB file

                .disableSubtitle()       // No subtiles

                .setAudioChannels(1)         // Mono audio
                .setAudioCodec("aac")        // using the aac codec
                .setAudioSampleRate(48_000)  // at 48KHz
                .setAudioBitRate(32768)      // at 32 kbit/s

                .setVideoCodec("libx264")     // Video using x264
                .setVideoFrameRate(24, 1)     // at 24 frames per second
                .setVideoResolution(640, 480) // at 640x480 resolution

                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        executor.createJob(builder).run()

// Or run a two-pass encode (which is better quality at the cost of being slower)
        executor.createTwoPassJob(builder).run()
    }
}
```
