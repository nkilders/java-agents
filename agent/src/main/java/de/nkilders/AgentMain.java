package de.nkilders;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

public class AgentMain {

  public static void main(String[] args) {
    IO.println("[Agent] main() called");
  }

  /**
   * Called on static attach via JVM argument
   */
  public static void premain(String agentArgs, Instrumentation inst) {
    IO.println("[Agent] premain() called");

    inst.addTransformer(new LoggingTransformer());
  }

  /**
   * Called on dynamic attach via Attach API
   */
  public static void agentmain(String agentArgs, Instrumentation inst) {
    IO.println("[Agent] agentmain() called");

    inst.addTransformer(new LoggingTransformer());
  }

  private static final class LoggingTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(
        ClassLoader loader,
        String className,
        Class<?> classBeingRedefined,
        java.security.ProtectionDomain protectionDomain,
        byte[] classfileBuffer) {
      IO.println("[Agent] Transforming class: " + className);
      return null; // Return null to indicate no transformation
    }
  }
}
