app_path=app/target/app-1.0.0-shaded.jar
agent_path=agent/target/agent-1.0.0-shaded.jar

echo "Starting app with agent..."
java -javaagent:$agent_path -jar $app_path
