package com.aliyun.odps.ml;

import java.util.Date;
import java.util.HashMap;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.aliyun.odps.LazyLoad;
import com.aliyun.odps.OdpsException;
import com.aliyun.odps.commons.transport.Headers;
import com.aliyun.odps.rest.JAXBUtils;
import com.aliyun.odps.rest.RestClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * OnlineModel表示ODPS中的在线模型
 *
 * @author chao.liu@alibaba-inc.com
 */
public class OnlineModel extends LazyLoad {

  @XmlRootElement(name = "Onlinemodel")
  static class OnlineModelDesc {

    @XmlElement(name = "Project")
    String project;

    @XmlElement(name = "Name")
    String modelName;

    @XmlElement(name = "Version")
    String version;

    @XmlElement(name = "Owner")
    String owner;

    @XmlElement(name = "CreateTime")
    @XmlJavaTypeAdapter(JAXBUtils.DateBinding.class)
    Date createTime;

    @XmlElement(name = "LastModifiedTime")
    @XmlJavaTypeAdapter(JAXBUtils.DateBinding.class)
    Date lastModifiedTime;

    @XmlElement(name = "OfflinemodelProject")
    String offlinemodelProject;

    @XmlElement(name = "OfflinemodelName")
    String offlinemodelName;

    @XmlElement(name = "OfflinemodelId")
    String offlinemodelId;

    @XmlElement(name = "ApplyRes")
    String applyRes;

    @XmlElement(name = "UsedRes")
    String usedRes;

    @XmlElement(name = "QOS")
    short applyQos;

    @XmlElement(name = "InstanceNum")
    short instanceNum;

    @XmlElement(name = "Status")
    String status;

    @XmlElement(name = "ServiceTag")
    String serviceTag;

    @XmlElement(name = "ServiceName")
    String serviceName;

    @XmlElement(name = "LastFailMsg")
    String lastFailMsg;

    @XmlElement(name = "PredictDesc")
    String predictDesc;

    @XmlElement(name = "ABTest")
    String ABTest;

    @XmlElement(name = "Runtime")
    String runtime;
  }

  private OnlineModelDesc modelDesc;
  private RestClient client;

  OnlineModel(OnlineModelDesc desc, RestClient client) {
    this.modelDesc = desc;
    this.client = client;
  }

  /**
   * 获取在线模型名
   *
   * @return 在线模型名称
   */
  public String getName() {
    return this.modelDesc.modelName;
  }

  @Deprecated
  public String getComment() {
    // for compatibility, cannot remove
    return null;
  }

  /**
   * 获取在线模型所属用户
   *
   * @return 所属用户
   */
  public String getOwner() {
    if (this.modelDesc.owner == null && client != null) {
      lazyLoad();
    }
    return this.modelDesc.owner;
  }

  /**
   * 获取创建时间
   *
   * @return 创建时间
   */
  public Date getCreatedTime() {
    if (this.modelDesc.createTime == null && client != null) {
      lazyLoad();
    }
    return this.modelDesc.createTime;
  }

  /**
   * 获取最后修改时间
   *
   * @return 最后修改时间
   */
  public Date getLastModifiedTime() {
    if (this.modelDesc.lastModifiedTime == null && client != null) {
      lazyLoad();
    }
    return this.modelDesc.lastModifiedTime;
  }

  /**
   * 获取在线模型所属Project名称
   *
   * @return Project名称
   */
  public String getProject() {
    return this.modelDesc.project;
  }

  /**
   * 获取被发布到在线的离线模型所属Project名称
   *
   * @return 在线模型的训练参数
   */
  public String getOfflineModelProject() {
    lazyLoad();
    return this.modelDesc.offlinemodelProject;
  }

  /**
   * 获取被发布到在线的离线模型名称
   *
   * @return 在线模型的训练参数
   */
  public String getOfflineModelName() {
    lazyLoad();
    return this.modelDesc.offlinemodelName;
  }

  /**
   * 获取被发布到在线的离线模型Id
   *
   * @return 在线模型的训练参数
   */
  public String getOfflineModelId() {
    lazyLoad();
    return this.modelDesc.offlinemodelId;
  }

  /**
   * 获取在线模型的实例数目
   *
   * @return 模型部署的instnace数目
   */
  public long getInstanceNum() {
    lazyLoad();
    return this.modelDesc.instanceNum;
  }

  /**
   * 获取在线模型的状态
   *
   * @return 模型状态
   */
  public OnlineStatus getStatus() {
    lazyLoad();
    return OnlineStatus.valueOf(this.modelDesc.status.toUpperCase());
  }

  /**
   * 获取使用的资源
   *
   * @return 使用资源描述
   */
  public ModelResource getUsedResource() {
    if (this.modelDesc.usedRes == null && client != null) {
      lazyLoad();
    }
    return new GsonBuilder().disableHtmlEscaping().create()
            .fromJson(this.modelDesc.usedRes, ModelResource.class);
  }

  /**
   * 获取申请的资源
   *
   * @return 申请资源描述
   */
  public ModelResource getApplyResource() {
    if (this.modelDesc.applyRes == null && client != null) {
      lazyLoad();
    }
    return new GsonBuilder().disableHtmlEscaping().create()
            .fromJson(this.modelDesc.applyRes, ModelResource.class);
  }

  /**
   * 获取模型版本信息
   *
   * @return 版本信息
   */
  public String getVersion() {
    if (this.modelDesc.version == null && client != null) {
      lazyLoad();
    }
    return this.modelDesc.version;
  }

  /**
   * 获取申请QPS信息
   *
   * @return qps信息
   */
  @Deprecated
  public long getApplyQPS() {
    lazyLoad();
    return this.modelDesc.applyQos;
  }

  /**
   * 获取申请QOS信息
   *
   * @return qos信息
   */
  public long getApplyQOS() {
    lazyLoad();
    return this.modelDesc.applyQos;
  }

  /**
   * 获取部署Service Tag
   *
   * @return 在线模型的Service Tag
   */
  public String getServiceTag() {
    lazyLoad();
    return this.modelDesc.serviceTag;
  }

  /**
   * 获取部署Service Name
   *
   * @return 在线模型的Service Name
   */
  public String getServiceName() {
    lazyLoad();
    return this.modelDesc.serviceName;
  }

  /**
   * 获取对模型操作过程中的失败信息
   *
   * @return 失败信息
   */
  public String getFaliedMsg() {
    if (this.modelDesc.lastFailMsg == null && client != null) {
      lazyLoad();
    }
    return this.modelDesc.lastFailMsg;
  }

  /**
   * 获取ABTest配置
   *
   * @return ABTest配置
   */
  public String getABTest() {
    lazyLoad();
    return this.modelDesc.ABTest;
  }

  /**
   * 获取processor pipeline信息
   *
   * @return processor pipeline信息
   */
  public String getPredictDesc() {
    lazyLoad();
    return this.modelDesc.predictDesc;
  }

  /**
   * 获取在线模型运行时状态
   *
   * @return 在线模型的运行时状态
   */
  public String getRuntime() {
    lazyLoad();
    return this.modelDesc.runtime;
  }

  /**
   * 更新在线模型
   *
   * @param qos
   *     qos
   * @return {@link OnlineModel}对象
   * @throws OdpsException
   */
  public void updateQos(short qos) throws OdpsException {
    update(this.modelDesc.offlinemodelProject,
           this.modelDesc.offlinemodelName,
           qos,
           this.modelDesc.instanceNum);
  }

  public void updateInstanceNumber(short instanceNum) throws OdpsException {
    update(this.modelDesc.offlinemodelProject,
           this.modelDesc.offlinemodelName,
           this.modelDesc.applyQos,
           instanceNum);
  }

  /**
   * 更新在线模型
   *
   * @param offlinemodelProject
   *     离线模型
   * @param offlinemodelName
   *     离线模型名字
   * @return {@link OnlineModel}对象
   * @throws OdpsException
   */
  public void update(String offlinemodelProject, String offlinemodelName)
      throws OdpsException {
    update(offlinemodelProject, offlinemodelName,
           this.modelDesc.applyQos, this.modelDesc.instanceNum);
  }

  /**
   * 更新在线模型
   *
   * @param offlinemodelProject
   *     离线模型
   * @param offlinemodelName
   *     离线模型名字
   * @param qos
   *     qos
   * @return {@link OnlineModel}对象
   * @throws OdpsException
   */
  public void update(String offlinemodelProject, String offlinemodelName,
                     short qos, short instanceNum) throws OdpsException {
    if (offlinemodelProject == null || offlinemodelProject.equals("")) {
      throw new IllegalArgumentException("offlinemodelProject required.");
    }
    if (offlinemodelName == null || offlinemodelName.equals("")) {
      throw new IllegalArgumentException("offlinemodelName required.");
    }

    OnlineModelInfo modelInfo = new OnlineModelInfo();
    modelInfo.offlineModelName = offlinemodelName;
    modelInfo.offlineProject = offlinemodelProject;
    modelInfo.QOS = qos;
    modelInfo.instanceNum = instanceNum;

    updateInternally(modelInfo);
  }

  public void update(OnlineModelInfo modelInfo) throws OdpsException {
    updateInternally(modelInfo);
  }

  public void update(OnlineModelInfoNew modelInfo) throws OdpsException {
    updateInternally(modelInfo);
  }

  public void update(ModelAbTestInfo abTestInfo) throws OdpsException {
    String xml = null;
    try {
      xml = JAXBUtils.marshal(abTestInfo, ModelAbTestInfo.class);
    } catch (JAXBException e) {
      throw new OdpsException(e.getMessage(), e);
    }

    HashMap<String, String> headers = new HashMap<String, String>();
    headers.put(Headers.CONTENT_TYPE, "application/xml");

    String
        resource =
        ModelResourceBuilder.buildOnlineModelResource(modelDesc.project, modelDesc.modelName);
    client.stringRequest(resource, "PUT", null, headers, xml);
  }


  @Override
  public void reload() throws OdpsException {
    String resource = ModelResourceBuilder.buildOnlineModelResource(
        modelDesc.project, modelDesc.modelName);
    modelDesc = client.request(OnlineModelDesc.class, resource, "GET");
  }

  private void updateInternally(OnlineModelInfo modelInfo) throws OdpsException {
    String xml = null;
    try {
      xml = JAXBUtils.marshal(modelInfo, OnlineModelInfo.class);
    } catch (JAXBException e) {
      throw new OdpsException(e.getMessage(), e);
    }

    HashMap<String, String> headers = new HashMap<String, String>();
    headers.put(Headers.CONTENT_TYPE, "application/xml");

    String resource = ModelResourceBuilder.buildOnlineModelResource(
        modelDesc.project, modelDesc.modelName);
    client.stringRequest(resource, "PUT", null, headers, xml);

    this.modelDesc.offlinemodelName = modelInfo.offlineModelName;
    this.modelDesc.offlinemodelProject = modelInfo.offlineProject;
    this.modelDesc.applyQos = modelInfo.QOS;
    this.modelDesc.instanceNum = modelInfo.instanceNum;
  }

  private void updateInternally(OnlineModelInfoNew modelInfo) throws OdpsException {
    String xml = null;
    try {
      xml = JAXBUtils.marshal(modelInfo, OnlineModelInfoNew.class);
    } catch (JAXBException e) {
      throw new OdpsException(e.getMessage(), e);
    }

    HashMap<String, String> headers = new HashMap<String, String>();
    headers.put(Headers.CONTENT_TYPE, "application/xml");

    String resource = ModelResourceBuilder.buildOnlineModelResource(
        modelDesc.project, modelDesc.modelName);
    client.stringRequest(resource, "PUT", null, headers, xml);

    this.modelDesc.applyQos = modelInfo.QOS;
    this.modelDesc.instanceNum = modelInfo.instanceNum;
  }
}
