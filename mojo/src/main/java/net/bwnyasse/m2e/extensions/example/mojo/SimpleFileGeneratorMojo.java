package net.bwnyasse.m2e.extensions.example.mojo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

/**
 * @author bwnyasse
 */
@Mojo(name = "m2e-extensions-example", requiresDependencyResolution = ResolutionScope.COMPILE, defaultPhase = LifecyclePhase.PROCESS_RESOURCES, threadSafe = true)
public class SimpleFileGeneratorMojo extends AbstractMojo
{

	/** The current maven project that execute this mojo. */
	@Component
	private MavenProject mavenProject;

	/** Some output directory. */
	@Parameter(property = "outputDirectory", defaultValue = "${project.build.directory}/example")
	private File outputDirectory;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException
	{

		initializeOutputDirectory();
		
		try
		{
			FileUtils.writeStringToFile(new File(outputDirectory, "myFile.txt"), new Date().toString());
		}
		catch (IOException e)
		{
			throw new MojoExecutionException(
					"An error has occured while writing into output file", e);
		}
	}

	/**
	 * Initialize output directory.
	 * 
	 * @throws MojoExecutionException
	 */
	private void initializeOutputDirectory() throws MojoExecutionException
	{
		try
		{
			Files.createDirectories(outputDirectory.toPath());
		}
		catch (IOException e)
		{
			throw new MojoExecutionException(
					"An error has occured while creating output directory.", e);
		}
	}
}
