package de.nkilders.transformer;

import java.security.ProtectionDomain;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

public class TracingTransformer implements AgentBuilder.Transformer {

  @Override
  public Builder<?> transform(
      Builder<?> builder,
      TypeDescription typeDescription,
      ClassLoader classLoader,
      JavaModule module,
      ProtectionDomain protectionDomain) {
    return builder.visit(Advice.to(TracingAdvice.class).on(ElementMatchers.isMethod()));
  }

  public static final class TracingAdvice {

    private TracingAdvice() {}

    @Advice.OnMethodEnter
    public static long enter(@Advice.Origin("#t.#m") String method) {
      var start = System.nanoTime();
      IO.println("ENTER " + method);
      return start;
    }

    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void exit(@Advice.Origin("#t.#m") String method, @Advice.Enter long start) {
      var dur = (System.nanoTime() - start) / 1_000_000.0;
      var msg = "EXIT %s dur=%f ms".formatted(method, dur);
      IO.println(msg);
    }
  }
}
