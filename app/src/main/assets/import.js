const admin = require('firebase-admin');
const fs = require('fs');

// Cargar las credenciales de Firebase
const serviceAccount = require('./cafeterias-7ddca-firebase-adminsdk-2se0e-cd89ee3d45.json');

// Inicializar la app de Firebase
admin.initializeApp({
    credential: admin.credential.cert(serviceAccount)
});

const db = admin.firestore();

// Leer el archivo JSON con los datos que quieres subir
const data = JSON.parse(fs.readFileSync('data.json', 'utf8'));

// Subir cada documento del JSON a Firestore
const collectionName = 'cafeterias'; // Cambia esto al nombre de la colección
data.forEach(async (doc) => {
    try {
        await db.collection(collectionName).add({
        id: doc.id,
        nombre: doc.nombre,
        direccion: doc.direccion,
        instagram: doc.instagram,
        imagen: doc.imagen,
        coordenadas: new admin.firestore.GeoPoint(doc.coordenadas.latitud, doc.coordenadas.longitud)
        });
        console.log('Documento agregado:', doc);
    } catch (error) {
        console.error('Error al agregar documento:', error);
    }
});