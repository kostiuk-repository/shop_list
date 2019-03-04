package com.olga.shoplist.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.olga.shoplist.domain.entitys.Archive;
import com.olga.shoplist.domain.entitys.ArchiveProduct;
import com.olga.shoplist.domain.repositorys.ArchiveRepository;

import java.util.List;


public class ArchiveViewModel extends ViewModel {

    private ArchiveRepository repository;

    ArchiveViewModel(final ArchiveRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<ArchiveProduct>> getArchiveProducts(String archiveId) {
        return repository.getArchiveProducts(archiveId);
    }

    public LiveData<List<Archive>> getArchives() {
        return repository.getArchives();
    }

    public LiveData<Archive> getArchiveById(final String archiveId) {
        return repository.getArchive(archiveId);
    }

    public void addArchiveToDatabase(final Archive archive) {
        new AddArchiveTask().execute(archive);
    }

    private class AddArchiveTask extends AsyncTask<Archive, Void, Void> {

        @Override
        protected Void doInBackground(final Archive... archive) {
            repository.createNewArchive(archive[0]);
            return null;
        }
    }

    public void deleteArchive(final Archive archive) {
        final DeleteItemTask deleteItemTask = new DeleteItemTask();
        deleteItemTask.execute(archive);
    }

    private class DeleteItemTask extends AsyncTask<Archive, Void, Void> {

        @Override
        protected Void doInBackground(final Archive... archive) {
            repository.deleteArchive(archive[0]);
            return null;
        }
    }

    public void addArchiveProductToDatabase(final ArchiveProduct archiveProduct) {
        new AddArchiveProductTask().execute(archiveProduct);
    }

    private class AddArchiveProductTask extends AsyncTask<ArchiveProduct, Void, Void> {

        @Override
        protected Void doInBackground(final ArchiveProduct... archiveProducts) {
            repository.insertArchiveProduct(archiveProducts[0]);
            return null;
        }
    }

}
