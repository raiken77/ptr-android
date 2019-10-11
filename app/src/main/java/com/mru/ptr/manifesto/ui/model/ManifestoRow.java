package com.mru.ptr.manifesto.ui.model;

import androidx.annotation.DrawableRes;

/**
 * Created by Jonathan on 2019-10-10.
 */
public class ManifestoRow {
  public String manifestoTitle;
  public @DrawableRes int manifestoImage;

  public String getManifestoTitle() {
    return manifestoTitle;
  }

  public ManifestoRow setManifestoTitle(String manifestoTitle) {
    this.manifestoTitle = manifestoTitle;
    return this;
  }

  public int getManifestoImage() {
    return manifestoImage;
  }

  public ManifestoRow setManifestoImage(int manifestoImage) {
    this.manifestoImage = manifestoImage;
    return this;
  }
}
