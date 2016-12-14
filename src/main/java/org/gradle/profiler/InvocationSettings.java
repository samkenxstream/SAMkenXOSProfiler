package org.gradle.profiler;

import java.io.File;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

public class InvocationSettings {
    private final File projectDir;
    private final Profiler profiler;
    private final Object profilerOptions;
    private final boolean benchmark;
    private final boolean dryRun;
    private final File scenarioFile;
    private final File outputDir;
    private final Invoker invoker;
    private final List<String> versions;
    private final List<String> targets;
    private final Map<String, String> sysProperties;
    private final File gradleUserHome;

    public InvocationSettings(File projectDir, Profiler profiler, final Object profilerOptions, boolean benchmark, File outputDir, Invoker invoker, boolean dryRun, File scenarioFile, List<String> versions, List<String> getTargets, Map<String, String> sysProperties, File gradleUserHome) {
        this.profilerOptions = profilerOptions;
        this.benchmark = benchmark;
        this.projectDir = projectDir;
        this.profiler = profiler;
        this.outputDir = outputDir;
        this.invoker = invoker;
        this.dryRun = dryRun;
        this.scenarioFile = scenarioFile;
        this.versions = versions;
        this.targets = getTargets;
        this.sysProperties = sysProperties;
        this.gradleUserHome = gradleUserHome;
    }

    public File getOutputDir() {
        return outputDir;
    }

    public Invoker getInvoker() {
        return invoker;
    }

    public boolean isDryRun() {
        return dryRun;
    }

    public boolean isBenchmark() {
        return benchmark;
    }

    public boolean isProfile() {
        return profiler != Profiler.NONE;
    }

    public Profiler getProfiler() {
        return profiler;
    }

    public Object getProfilerOptions() {
        return profilerOptions;
    }

    public File getScenarioFile() {
        return scenarioFile;
    }

    public File getProjectDir() {
        return projectDir;
    }

    public List<String> getVersions() {
        return versions;
    }

    public List<String> getTargets() {
        return targets;
    }

    public Map<String, String> getSystemProperties() {
        return sysProperties;
    }

    public int getWarmUpCount() {
        return dryRun ? 1 : 2;
    }

    public int getBuildCount() {
        return (benchmark && !dryRun) ? 13 : 1;
    }

    public File getGradleUserHome() {
        return gradleUserHome;
    }
    
    public void printTo(PrintStream out) {
        out.println();
        out.println("* Settings");
        out.println("Project dir: " + getProjectDir());
        out.println("Output dir: " + getOutputDir());
        out.println("Profile: " + isProfile());
        out.println("Benchmark: " + isBenchmark());
        out.println("Versions: " + getVersions());
        out.println("Gradle User Home: " + getGradleUserHome());
        out.println("Targets: " + getTargets());
        if (!getSystemProperties().isEmpty()) {
            out.println("System properties:");
            for (Map.Entry<String, String> entry : getSystemProperties().entrySet()) {
                out.println("  " + entry.getKey() + "=" + entry.getValue());
            }
        }
    }
}