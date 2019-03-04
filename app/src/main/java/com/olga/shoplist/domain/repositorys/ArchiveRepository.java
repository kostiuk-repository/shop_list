package com.olga.shoplist.domain.repositorys;

import android.arch.lifecycle.LiveData;

import com.olga.shoplist.domain.dao.ArchiveDao;
import com.olga.shoplist.domain.entitys.Archive;
import com.olga.shoplist.domain.entitys.ArchiveProduct;

import java.util.List;

import javax.inject.Inject;

public class ArchiveRepository {

    private final ArchiveDao archiveDao;

    @Inject
    public ArchiveRepository(final ArchiveDao archiveDao) {
        this.archiveDao = archiveDao;
    }

    public LiveData<List<ArchiveProduct>> getArchiveProducts(String id) {
        return archiveDao.getArchiveProducts(id);
    }

    public LiveData<List<Archive>> getArchives() {
        return archiveDao.getArchives();
    }

    public LiveData<Archive> getArchive(final String archiveId) {
        return archiveDao.getArchiveById(archiveId);
    }

    public Long createNewArchive(final Archive archive) {
        return archiveDao.insertArchive(archive);
    }

    public void deleteArchive(final Archive archive) {
        archiveDao.deleteArchive(archive);
    }

    public void insertArchiveProduct(final ArchiveProduct archiveProduct) {
        archiveDao.insertArchiveProduct(archiveProduct);
    }

}
