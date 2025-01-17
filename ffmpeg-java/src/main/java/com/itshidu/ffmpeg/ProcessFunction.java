package com.itshidu.ffmpeg;

import java.io.IOException;
import java.util.List;

/**
 * Runs a process returning a Reader to its stdout
 */
public interface ProcessFunction {
  Process run(List<String> args) throws IOException;
}
