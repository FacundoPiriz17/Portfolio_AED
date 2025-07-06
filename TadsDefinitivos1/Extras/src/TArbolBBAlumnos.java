// Se puede modificar la clase de la cual se hereda si se considera necesario
public class TArbolBBAlumnos extends TArbolBB<Alumno> implements IArbolBBAlumnos {

    @Override
    public void armarSubgrupos(TArbolBB<Alumno> grupoImpares, TArbolBB<Alumno> grupoPares) {
        if (this.getRaiz() != null) this.getRaiz().armarSubgrupos(grupoImpares, grupoPares, 0);
    }

}