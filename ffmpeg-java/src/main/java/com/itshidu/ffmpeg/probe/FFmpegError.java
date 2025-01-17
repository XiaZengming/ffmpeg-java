package com.itshidu.ffmpeg.probe;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(
  value = {"UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD"},
  justification = "POJO objects where the fields are populated by gson"
)
public class FFmpegError {
  public int code;
  public String string;
}
