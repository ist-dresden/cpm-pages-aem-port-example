package composum.prototype.aemwcmcorereplacement.aitasks;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Makes it easy to find files to process with {@link AITask} etc.
 */
public class AIFileRepository {

    public static final String HTML_PATTERN = ".*\\.html";
    private static final Logger LOG = LoggerFactory.getLogger(AIFileRepository.class);
    private final File directory;

    private AIFileRepository(String path) {
        sanityCheck();
        try {
            directory = new File(path).getAbsoluteFile().getCanonicalFile();
        } catch (IOException e) {
            throw new IllegalStateException("Path " + path + " does not exist", e);
        }
        if (!directory.isDirectory()) {
            throw new IllegalStateException("Directory " + directory + " does not exist");
        }
    }

    private static void sanityCheck() {
        if (!new File("src/main/java").isDirectory() || !new File("pom.xml").isFile()) {
            // This might be actually OK, but seems more likely to be a mistake. Let's see.
            throw new IllegalStateException("Something is wrong - we are not started in the maven project, but " + new File(".").getAbsolutePath());
        }
    }

    public static AIFileRepository fromPath(String... relativePaths) {
        StringBuilder path = new StringBuilder();
        for (String relativePath : relativePaths) {
            path.append(relativePath).append("/");
        }
        return new AIFileRepository(path.toString());
    }

    /**
     * Make repository from environment variable.
     */
    public static AIFileRepository fromEnv(@Nonnull String envVar, @Nullable String relativePath) {
        String path = System.getenv(envVar);
        if (path == null) {
            throw new IllegalStateException("Environment variable " + envVar + " not set");
        }
        if (relativePath != null) {
            path = path + "/" + relativePath;
        }
        return new AIFileRepository(path);
    }

    /**
     * File relative to repository root - that doesn't need to exist (might be output file).
     */
    public File file(String relpath) {
        return new File(directory, relpath);
    }

    /**
     * Files in a directory, matching a regex.
     */
    public List<File> files(@Nonnull String relpathDirectory, @Nullable String filePathRegex, boolean recursive) {
        File dir = new File(directory, relpathDirectory);
        if (!dir.isDirectory()) {
            throw new IllegalStateException("Directory " + dir + " does not exist");
        }
        List<File> result = new ArrayList<>();
        Pattern filePathPattern = filePathRegex != null ? Pattern.compile(filePathRegex) : Pattern.compile(".*");
        if (!recursive) {
            File[] files = dir.listFiles((dir1, name) -> filePathPattern.matcher(dir1 + "/" + name).matches());
            Arrays.stream(Objects.requireNonNull(files, dir.toString()))
                    .filter(File::isFile).forEach(result::add);
        } else {
            try {
                Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if (filePathPattern.matcher(file.getFileName().toString()).matches()) {
                            result.add(file.toFile());
                        }
                        return super.visitFile(file, attrs);
                    }
                });
            } catch (IOException e) {
                LOG.error("for " + dir, e);
            }
        }
        return result;
    }

    /**
     * All files matching a filePathRegex that contain a pattern.
     */
    public List<File> filesContaining(@Nonnull String relpathDirectory, @Nonnull String filePathRegex, @Nonnull String pattern, boolean recursive) {
        List<File> candidates = files(relpathDirectory, filePathRegex, recursive);
        List<File> result = new ArrayList<>();
        Pattern patternPattern = Pattern.compile(pattern);
        for (File file : candidates) {
            try {
                String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                if (patternPattern.matcher(content).find()) {
                    result.add(file);
                }
            } catch (IOException e) {
                LOG.error("for " + file, e);
            }
        }
        return result;
    }

    /**
     * File from full java class name.
     */
    @Nonnull
    public File javaFile(@Nonnull String fullName) {
        return new File(directory, fullName.replaceAll("[.]", "/") + ".java");
    }

    /**
     * File for documenting a full java class name.
     */
    @Nonnull
    public File javaMdFile(@Nonnull String fullName) {
        return new File(directory, fullName.replaceAll("[.]", "/") + ".md");
    }

}
