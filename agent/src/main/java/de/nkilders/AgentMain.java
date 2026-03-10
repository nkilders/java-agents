package de.nkilders;

import java.lang.instrument.Instrumentation;

import de.nkilders.transformer.TracingTransformer;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.matcher.ElementMatchers;

public class AgentMain {

  public static void main(String[] args) {
    IO.println("[Agent] main() called");
  }

  /**
   * Called on static attach via JVM argument
   */
  public static void premain(String agentArgs, Instrumentation inst) {
    IO.println("[Agent] premain() called");
    IO.println("[Agent] Agent args: " + agentArgs);

    startAgent(agentArgs, inst);
  }

  /**
   * Called on dynamic attach via Attach API
   */
  public static void agentmain(String agentArgs, Instrumentation inst) {
    IO.println("[Agent] agentmain() called");
    IO.println("[Agent] Agent args: " + agentArgs);

    startAgent(agentArgs, inst);
  }

  // ====================================================================================== //

  private static void startAgent(String agentArgs, Instrumentation inst) {
    // inst.addTransformer(new LoggingTransformer());

    var ignore =
      ElementMatchers.nameStartsWith("net.bytebuddy.")
        .or(ElementMatchers.nameStartsWith("jdk."))
        .or(ElementMatchers.nameStartsWith("java."))
        .or(ElementMatchers.nameStartsWith("sun."));

    new AgentBuilder.Default().ignore(ignore)
      .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
      .type(ElementMatchers.any())
      .transform(new TracingTransformer())
      .installOn(inst);
  }
}
