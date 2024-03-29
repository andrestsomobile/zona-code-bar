﻿CREATE OR REPLACE FUNCTION public.actualiza_registro_pedido()
  RETURNS trigger AS
$BODY$--
--Trigger que actualiza el estado del registro_pedido a FINALIZADO si todos sus registro_pedido_detalle estan en rpdeterminado = "S"

DECLARE
  sin_terminar int;

BEGIN

  IF TG_RELNAME='registro_pedido_detalle' THEN
    IF new.rpdeterminado = 'S' THEN
      raise notice 'estoy en if termindado = s';
      UPDATE pedido SET pedestado = 'FINALIZADO' WHERE pedcodsx = new.rpdenumpedido;
      PERFORM automatico_posiciones_bodega();
      SELECT count(rpdecodsx) INTO sin_terminar FROM registro_pedido_detalle WHERE rpderegped = new.rpderegped AND rpdeterminado = 'N';
      IF sin_terminar IS NOT NULL AND sin_terminar = 0 THEN
        UPDATE registro_pedido SET repeesta = 'FINALIZADO',repehofi = CURRENT_TIME WHERE repecodsx = new.rpderegped;
      END IF;
    END IF;	
  END IF;
RETURN new;
END;

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.actualiza_registro_pedido()
  OWNER TO postgres;

--  select * from registro_pedido_detalle 

--  select * from pedido where pedcodsx in (126,128)
--  update registro_pedido_detalle set rpdeterminado = 'S' where rpdenumpedido in (126,128)