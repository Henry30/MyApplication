package com.henrymeza.mascotasbasedato.db;

/**
 * Created by henry on 15/10/2016.
 */

public final class ConstantesBaseDato {

    public static final String DATABASE_NAME="Mascota";
    public static final int DATABASE_VERSION=1;

    public static final String TABLE_MASCOTA="Mascota";
    public static final String TABLE_MASCOTA_ID="id";
    public static final String TABLE_MASCOTA_NOMBRE="nombre";
    public static final String TABLE_MASCOTA_FOTO="foto";

    public static final String TABLE_LIKE_MASCOTA="Mascota_Like";
    public static final String TABLE_LIKE_MASCOTA_ID="id";
    public static final String TABLE_LIKE_MASCOTA_ID_MASCOTA="id_Mascota";
    public static final String TABLE_LIKE_MASCOTA_NUMEROS_LIKE="numero_likes";
}
