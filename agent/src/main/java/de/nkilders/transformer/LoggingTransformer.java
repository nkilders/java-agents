package de.nkilders.transformer;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class LoggingTransformer implements ClassFileTransformer {

  @Override
  public byte[] transform(
      ClassLoader loader,
      String className,
      Class<?> classBeingRedefined,
      ProtectionDomain protectionDomain,
      byte[] classfileBuffer) {
    IO.println("[Agent] Transforming class: " + className);
    return null; // Return null to indicate no transformation
  }
}
