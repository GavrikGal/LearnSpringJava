package com.gmail.gal.gavrik.display.service;

import java.io.File;
import java.util.List;

public interface FileFinderService {

	public List<File> findAll(String startPath) throws Exception;

	public List<File> findAll(String startPath, String mask) throws Exception;

	public List<File> findFiles(String startPath) throws Exception;

	public List<File> findFiles(String startPath, String mask) throws Exception;

	public List<File> findDirectories(String startPath) throws Exception;

	public List<File> findDirectories(String startPath, String mask)
			throws Exception;

	public long getDirectorySize();

	public long getFilesNumber();

	public long getDirectoriesNumber();

}
