package net.bwnyasse.core;

import java.util.Set;

import org.apache.maven.plugin.MojoExecution;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.project.configurator.MojoExecutionBuildParticipant;

/**
 * @author bwnyasse
 */
public class MyBuildParticipant extends MojoExecutionBuildParticipant
{

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
		// TODO

		return null;
	}
}
