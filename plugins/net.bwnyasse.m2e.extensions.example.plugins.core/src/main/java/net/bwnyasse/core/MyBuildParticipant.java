package net.bwnyasse.core;

import java.util.Set;

import org.apache.maven.plugin.MojoExecution;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.project.configurator.MojoExecutionBuildParticipant;

/**
 * @author bwnyasse
 */
public class MyBuildParticipant extends MojoExecutionBuildParticipant
{

	private static final String MOJO_GAV = "";

	private static final String M2E_COMPLIANT_ATTRIBUTE = "m2eCompliant";

	public MyBuildParticipant(MojoExecution execution)
	{
		// Ensure execution while updating configuration and also on incremental modification
		super(execution, true, true);
	}

	/**
	 * 
	 * @see org.eclipse.m2e.core.project.configurator.MojoExecutionBuildParticipant#build(int,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 * @overrides
	 */
	@Override
	public Set<IProject> build(int kind, IProgressMonitor monitor) throws Exception
	{

		if (isM2ECompliant(getMavenProjectFacade().getMavenProject(monitor)))
		{
			// Concrete execution of the mojo
			return super.build(kind, monitor);
		}
		return null;
	}

	private boolean isM2ECompliant(MavenProject mavenProject)
	{
		Xpp3Dom configuration = (Xpp3Dom)mavenProject.getPlugin(MOJO_GAV).getConfiguration();
		if (configuration == null)
		{
			return false;
		}
		Xpp3Dom m2eCompliantXmlNode = configuration.getChild(M2E_COMPLIANT_ATTRIBUTE);
		return (m2eCompliantXmlNode != null) && Boolean.parseBoolean(m2eCompliantXmlNode.getValue());
	}
}
