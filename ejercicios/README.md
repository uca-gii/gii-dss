# Ejercicios de patrones de diseño

Resumen de los ejercicios planteados en esta carpeta:

- **Contabilidad de empleados**: aplicación de patrones para separar operaciones sobre tipos de empleados, combinando diseño con estructura compuesta.
- **Noticias**: suscripción y notificación de eventos con selección de canales de aviso.
- **Distribuidora**: automatización del procesamiento de formularios con flexibilidad para nuevas salidas y formas de presentación.

## Enunciados

- [Contabilidad de empleados](./ejercicioContabilidadEmpleados_enunciado/ejercicioContabilidadEmpleados_enunciado.md)
- [Noticias](./ejercicioNoticias_enunciado/ejercicioNoticias_enunciado.md)
- [Distribuidora](./ejercicioDistribuidora_enunciado/ejercicioDistribuidora_enunciado.md)

## Soluciones

- [Contabilidad de empleados](./ejercicioContabilidadEmpleados/ejercicioContabilidadEmpleados.md)
- [Noticias](./ejercicioNoticias/ejercicioNoticias.md)

## Generar PNG desde PUML

Para generar imágenes PNG a partir de archivos `.puml`, usa el script `scripts/puml_to_png.sh`.

Ejemplo de uso desde esta carpeta:

```bash
./scripts/puml_to_png.sh ./ejercicioNoticias/diagrama_clases_observer.puml
```

Uso general:

```bash
./scripts/puml_to_png.sh <archivo.puml> [salida.png]
```

Requisitos: `python3` y `curl` disponibles en el sistema.
