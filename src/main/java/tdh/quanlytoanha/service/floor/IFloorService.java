package tdh.quanlytoanha.service.floor;

import tdh.quanlytoanha.dtos.FloorDTO;
import tdh.quanlytoanha.service.IGeneralService;

public interface IFloorService extends IGeneralService<FloorDTO> {
    double getTheRestAreaOfFloor(Integer floorId);
}
