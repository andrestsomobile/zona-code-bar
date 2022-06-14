﻿-- FUNCTION: movimiento.flete_despacho()

-- DROP FUNCTION movimiento.flete_despacho();

CREATE OR REPLACE FUNCTION movimiento.flete_despacho()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
--
--Trigger que permite actualiza el costo del flete cuando se finaliza el despacho

DECLARE
rec record; -- registro de la tabla
costo numeric;
BEGIN

	IF TG_RELNAME='despacho' THEN
		IF (NEW.despestado = 'FINALIZADO' AND OLD.despestado = 'TRAMITE' 
			OR (NEW.despestado = 'FINALIZADO' AND NEW.desptransportadora != OLD.desptransportadora) 
			OR (NEW.despestado = 'FINALIZADO' AND NEW.desptipoveh != OLD.desptipoveh) )  THEN
			SELECT  DISTINCT despcodsx, despfecha, desptransportadora, desptipoveh, pedcliente, pedsucursal, flevalor INTO rec
				FROM despacho
				INNER JOIN despacho_pedido ON despcodsx = despeddespacho
				INNER JOIN pedido ON pedcodsx = despedpedido
				INNER JOIN flete ON flecliente = pedcliente AND flesucursal = pedsucursal AND fletransportadora = NEW.desptransportadora AND fletipoveh = NEW.desptipoveh
				WHERE despcodsx = NEW.despcodsx
				ORDER BY flevalor DESC
				LIMIT 1;
			IF FOUND THEN
				RAISE NOTICE 'Costo de flete es %',rec.flevalor;
				NEW.despcostoflete = rec.flevalor;
				NEW.despfechaflete = now();
			ELSE
				RAISE NOTICE 'Costo de flete no se encontro';
				NEW.despcostoflete = 0.0;
				NEW.despfechaflete = now();
			END IF;
		END IF;
	END IF;
	RETURN NEW;
END;
$BODY$;

ALTER FUNCTION movimiento.flete_despacho()
    OWNER TO postgres;


-- Trigger: resumen

 DROP TRIGGER IF EXISTS flete_despacho ON movimiento.despacho;

CREATE TRIGGER flete_despacho
    BEFORE UPDATE 
    ON movimiento.despacho
    FOR EACH ROW
    EXECUTE PROCEDURE movimiento.flete_despacho();    
