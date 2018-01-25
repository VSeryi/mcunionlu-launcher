package com.svaleror.unionlulauncher.logic;

import java.io.File;

import org.eclipse.jgit.util.FS_Win32;

public class FS_Win32_NoGit extends FS_Win32 {

	@Override
	protected File discoverGitExe() {
		return null;
	}

}
