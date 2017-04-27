package com.library.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.library.enums.LibraryEnum;
import com.library.service.LibraryService;

@Component
public class LibraryFactory {

	@Autowired
	private List<LibraryService> libraryServices;
	@Value("${isRestCall:NO}")
	private String isRestCall;

	public LibraryService getLibraryService() {

		LibraryService tempLibraryService = null;

		for (LibraryService libraryService : libraryServices) {

			if ("YES".equalsIgnoreCase(isRestCall)) {

				if (LibraryEnum.THROUGH_RESTTEMPLATE.equals(libraryService.getEnum())) {
					tempLibraryService = libraryService;
					break;
				}

			} else if ("NO".equalsIgnoreCase(isRestCall)) {
				if (LibraryEnum.DIRECT_DATABASE.equals(libraryService.getEnum())) {
					tempLibraryService = libraryService;
					break;
				}
			}

		}

		return tempLibraryService;

	}

}
